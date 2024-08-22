package com.wolfott.mangement.device.repositories;

import com.wolfott.mangement.device.models.Enigma2Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Enigma2DeviceRepository extends JpaRepository<Enigma2Device, Long>, JpaSpecificationExecutor<Enigma2Device> {
}
