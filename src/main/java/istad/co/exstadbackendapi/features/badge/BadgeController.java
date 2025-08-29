package istad.co.exstadbackendapi.features.badge;

import istad.co.exstadbackendapi.domain.Badge;
import istad.co.exstadbackendapi.features.badge.dto.BadgeRequest;
import istad.co.exstadbackendapi.features.badge.dto.BadgeUpdate;
import istad.co.exstadbackendapi.mapper.BadgeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/badges")
public class BadgeController {
    private final BadgeService badgeService;

    @GetMapping
    public ResponseEntity<?> getAllBadges(){
        return new ResponseEntity<>(
                Map.of("badges",badgeService.getAllBadges()), HttpStatus.OK
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getBadgeByUuid(@PathVariable String uuid){
        return new ResponseEntity<>(
                badgeService.getBadgeByUuid(uuid), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> createBadge(@RequestBody @Valid BadgeRequest badgeRequest){
        return new ResponseEntity<>(
                badgeService.createBadge(badgeRequest), HttpStatus.CREATED
        );
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateBadge(@PathVariable String uuid, @RequestBody @Valid BadgeUpdate badgeUpdate){
        return new ResponseEntity<>(
                badgeService.updateBadge(uuid, badgeUpdate), HttpStatus.OK
        );
    }

    @PutMapping("/delete/{uuid}")
    public ResponseEntity<?> deleteBadge(@PathVariable String uuid){
        return new ResponseEntity<>(
                badgeService.deleteBadge(uuid), HttpStatus.OK
        );
    }

    @PutMapping("/restore/{uuid}")
    public ResponseEntity<?> restoreBadge(@PathVariable String uuid){
        return new ResponseEntity<>(
                badgeService.restoreBadge(uuid), HttpStatus.OK
        );
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> hardDeleteBadge(@PathVariable String uuid){
        return new ResponseEntity<>(
                badgeService.hardDeleteBadge(uuid), HttpStatus.OK
        );
    }

}
