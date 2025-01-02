package com.wolfott.stream_mangement.repositories;

import com.wolfott.stream_mangement.models.OutputFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OutputFormatRepository extends JpaRepository<OutputFormat, Integer> {

    @Query("SELECT of FROM OutputFormat of WHERE of.outputName = :format")
    Optional<OutputFormat> findByOutputName(String format);
}
