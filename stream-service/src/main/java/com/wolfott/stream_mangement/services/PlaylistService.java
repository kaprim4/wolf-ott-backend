package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.models.OutputFormat;
import com.wolfott.stream_mangement.models.Stream;
import com.wolfott.stream_mangement.models.StreamCategory;
import com.wolfott.stream_mangement.repositories.CategoryRepository;
import com.wolfott.stream_mangement.repositories.OutputFormatRepository;
import com.wolfott.stream_mangement.repositories.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    private static final String BASE_URL = "http://r2u.tech:80/O6rUaEWw/E82yNpefg9c/";

    @Autowired
    private StreamRepository streamRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OutputFormatRepository outputFormatRepository;

    public String generateM3UPlaylist(String format) {

        StringBuilder sb = new StringBuilder();
        Optional<OutputFormat> outputFormat = outputFormatRepository.findByOutputName(format);

        if(outputFormat.isPresent()) {
            List<Stream> streams = streamRepository.findAll();

            sb.append("#EXTM3U\n");
            sb.append("#EXT-X-SESSION-DATA:DATA-ID=\"com.xui.1_5_12r2\"\n");

            for (Stream s : streams) {

                String rawCategory = s.getCategoryId();
                Long catId = extractFirstCategoryId(rawCategory);

                String groupTitle = "Sans catégorie";
                if (catId != null) {
                    Optional<StreamCategory> optCat = categoryRepository.findById(catId);
                    if (optCat.isPresent()) {
                        groupTitle = optCat.get().getCategoryName();
                    }
                }

                String realSource = BASE_URL + (s.getChannelId() != null ? s.getChannelId() : s.getId());

                sb.append("#EXTINF:-1 tvg-id=\"\" tvg-name=\"")
                        .append(s.getStreamDisplayName() != null ? s.getStreamDisplayName() : "")
                        .append("\" tvg-logo=\"")
                        .append(s.getStreamIcon() != null ? s.getStreamIcon() : "")
                        .append("\" group-title=\"")
                        .append(groupTitle)
                        .append("\",")
                        .append(s.getStreamDisplayName() != null ? s.getStreamDisplayName() : "")
                        .append("\n");

                sb.append(realSource);

                sb.append(format.equals("HLS") ? "." + outputFormat.get().getOutputExt() : "").append("\n");
            }
        }
        return sb.toString();
    }

    private Long extractFirstCategoryId(String rawCategory) {
        if (rawCategory == null || rawCategory.isEmpty()) {
            return null;
        }
        String trimmed = rawCategory.replaceAll("[\\[\\]]", ""); // "117" ou "117,118"
        String[] parts = trimmed.split(",");
        if (parts.length > 0) {
            try {
                return Long.valueOf(parts[0].trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}

