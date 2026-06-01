controller/GhostNetController.java
package de.sheasepherd.ghostnet.controller;

import de.sheasepherd.ghostnet.entity.GhostNet;
import de.sheasepherd.ghostnet.enums.GhostNetStatus;
import de.sheasepherd.ghostnet.repository.GhostNetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GhostNetController {

    private final GhostNetRepository ghostNetRepository;

    public GhostNetController(GhostNetRepository ghostNetRepository) {
        this.ghostNetRepository = ghostNetRepository;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/report-net";
    }

    @GetMapping("/report-net")
    public String showReportForm(Model model) {
        model.addAttribute("ghostNet", new GhostNet());
        return "report-net";
    }

    @PostMapping("/report-net")
    public String reportGhostNet(@ModelAttribute GhostNet ghostNet) {
        ghostNet.setStatus(GhostNetStatus.GEMELDET);

        if (ghostNet.isAnonymous()) {
            ghostNet.setReporterName(null);
            ghostNet.setReporterPhone(null);
        }

        ghostNetRepository.save(ghostNet);

        return "redirect:/report-net";
    }
}
