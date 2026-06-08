repository/GhostNetRepository.java
package de.sheasepherd.ghostnet.repository;

import de.sheasepherd.ghostnet.entity.GhostNet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {
}
package de.sheasepherd.ghostnet.repository;

import de.sheasepherd.ghostnet.entity.GhostNet;
import de.sheasepherd.ghostnet.enums.GhostNetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {

     List<GhostNet> findByStatusIn(List<GhostNetStatus> statuses);
}
