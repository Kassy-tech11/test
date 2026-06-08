package de.seashepard.ghostnet.controller;

import de.seashepard.ghostnet.entity.GhostNet;
import de.seashepard.ghostnet.entity.Rescuer;
import de.seashepard.ghostnet.enums.GhostNetStatus;
import de.seashepard.ghostnet.repository.GhostNetRepository;
import de.seashepard.ghostnet.repository.RescuerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class GhostNetController {

    private final GhostNetRepository ghostNetRepository;
    private final RescuerRepository rescuerRepository;

    public GhostNetController(GhostNetRepository ghostNetRepository,
                              RescuerRepository rescuerRepository) {
        this.ghostNetRepository = ghostNetRepository;
        this.rescuerRepository = rescuerRepository;
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
        return "redirect:/open-nets";
    }

   @GetMapping("/open-nets")
public String showOpenNets(Model model) {

    model.addAttribute(
            "ghostNets",
            ghostNetRepository.findAll()
    );

    return "open-nets";
}

    @GetMapping("/assign-net/{id}")
    public String showAssignForm(@PathVariable Long id, Model model) {
        GhostNet ghostNet = ghostNetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Geisternetz nicht gefunden"));

        model.addAttribute("ghostNet", ghostNet);
        model.addAttribute("rescuer", new Rescuer());

        return "assign-net";
    }

    @PostMapping("/assign-net/{id}")
    public String assignNet(@PathVariable Long id,
                            @ModelAttribute Rescuer rescuer) {

        GhostNet ghostNet = ghostNetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Geisternetz nicht gefunden"));

        Rescuer savedRescuer = rescuerRepository.save(rescuer);

        ghostNet.setRescuer(savedRescuer);
        ghostNet.setStatus(GhostNetStatus.BERGUNG_BEVORSTEHEND);

        ghostNetRepository.save(ghostNet);

        return "redirect:/open-nets";
    }
    @PostMapping("/mark-recovered/{id}")
    public String markRecovered(@PathVariable Long id) {
    GhostNet ghostNet = ghostNetRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Geisternetz nicht gefunden"));

    ghostNet.setStatus(GhostNetStatus.GEBORGEN);
    ghostNetRepository.save(ghostNet);

    return "redirect:/open-nets";
    }
}
