package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.exceptions.PackageNotFoundException;
import com.wolfott.mangement.user.mappers.PackageMapper;
import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.models.UserPackage;
import com.wolfott.mangement.user.repositories.PackageRepository;
import com.wolfott.mangement.user.requests.PackageCreateRequest;
import com.wolfott.mangement.user.requests.PackageUpdateRequest;
import com.wolfott.mangement.user.responses.*;
import com.wolfott.mangement.user.specifications.PackageSpecification;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    PackageRepository packageRepository;
    @Autowired
    PackageSpecification packageSpecification;
    @Autowired
    PackageMapper packageMapper;
    @Autowired
    UserService userService;


    @Override
    public PackageDetailResponse getOne(String id) {
        UserPackage pkg = packageRepository.findById(id).orElseThrow(() -> new PackageNotFoundException("Package Not Found"));
        return packageMapper.toDetailResponse(pkg);
    }

    @Override
    public List<PackageCompactResponse> getAll(Map<String, Object> filters) {
        Specification<UserPackage> spec = packageSpecification.dynamic(filters);
        List<UserPackage> list = packageRepository.findAll(spec);
        return packageMapper.toCompactResponse(list);
    }

    @Override
    public Page<PackageCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<UserPackage> spec = packageSpecification.dynamic(filters);
        Page<UserPackage> page = packageRepository.findAll(spec, pageable);
        return packageMapper.toCompactResponse(page);
    }

    @Override
    public List<PackageCompactResponse> getAllByUser(Long id) {
        UserDetailResponse user = userService.getOne(id);
        List<UserPackage> packageList = packageRepository.findByGroup(user.getGroupId());
        return packageMapper.toCompactResponse(packageList);
    }

    @Override
    public Page<PackageCompactResponse> getAllByUser(Long id, Map<String, Object> filters, Pageable pageable) {
        UserDetailResponse user = userService.getOne(id);
        List<UserPackage> packageList = packageRepository.findByGroup(user.getGroupId());
        Page<UserPackage> page = new PageImpl<>(packageList, pageable, packageList.size());
        return packageMapper.toCompactResponse(page);
    }

    @Override
    public PackageCreateResponse create(PackageCreateRequest request) {
        UserPackage pkg = packageMapper.toPackage(request);
        pkg = packageRepository.save(pkg);
        return packageMapper.toCreateResponse(pkg);
    }

    @Override
    public PackageUpdateResponse update(String id, PackageUpdateRequest request) {
        UserPackage pkg = packageMapper.toPackage(request);
        pkg.setId(id);
        pkg = packageRepository.save(pkg);
        return packageMapper.toUpdateResponse(pkg);
    }

    @Override
    public void delete(String id) {
        packageRepository.deleteById(id);
    }
}
