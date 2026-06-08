repository/GhostNetRepository.java
package de.seashepard.ghostnet.repository;

import de.seashepard.ghostnet.entity.GhostNet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {
}
package de.seashepard.ghostnet.repository;

import de.seashepard.ghostnet.entity.GhostNet;
import de.seashepard.ghostnet.enums.GhostNetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {

     List<GhostNet> findByStatusIn(List<GhostNetStatus> statuses);
}
