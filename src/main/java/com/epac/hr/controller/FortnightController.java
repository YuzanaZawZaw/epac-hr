package com.epac.hr.controller;

import com.epac.hr.entity.Fortnight;
import com.epac.hr.repository.FortnightRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fortnights")
public class FortnightController {

    private final FortnightRepository fortnightRepository;

    public FortnightController(FortnightRepository fortnightRepository) {
        this.fortnightRepository = fortnightRepository;
    }

    @GetMapping
    public List<FortnightDto> list() {
        return fortnightRepository.findAll().stream()
                .sorted(Comparator.comparing(Fortnight::getStartDate).reversed())
                .map(f -> new FortnightDto(f.getFortnightId(), f.getFortnightName(), f.getStartDate().toString(), f.getEndDate().toString()))
                .collect(Collectors.toList());
    }

    public static record FortnightDto(Integer id, String name, String startDate, String endDate) {}
}
