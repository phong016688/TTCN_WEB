package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.OccasionDTO;
import storysflower.com.storysflower.repositories.OccasionRepository;
import storysflower.com.storysflower.services.OccasionService;

import java.util.Collections;
import java.util.List;

/**
 * @author ntynguyen
 */

@Component
public class OccasionServiceImpl implements OccasionService {
    @Autowired
    OccasionRepository occasionRepository;

    @Override
    public List<OccasionDTO> findAllOccasion() {
        List<OccasionDTO> occasionDTOS = occasionRepository.findAllOccasion();
        return occasionDTOS == null ? Collections.emptyList() : occasionDTOS;
    }

    @Override
    public OccasionDTO getOccasionDTOById(Long occasionId) {
        return occasionRepository.getOccasionDTOById(occasionId);
    }
}
