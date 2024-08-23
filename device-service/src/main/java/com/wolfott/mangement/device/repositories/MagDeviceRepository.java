package com.wolfott.mangement.device.repositories;

import com.wolfott.mangement.device.models.MagDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MagDeviceRepository extends JpaRepository<MagDevice, Long>, JpaSpecificationExecutor<MagDevice> {
}
