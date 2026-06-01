repository/GhostNetRepository.java
package de.sheasepherd.ghostnet.repository;

import de.sheasepherd.ghostnet.entity.GhostNet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {
}
