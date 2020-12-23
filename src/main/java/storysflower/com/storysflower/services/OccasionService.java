package storysflower.com.storysflower.services;

import storysflower.com.storysflower.dto.OccasionDTO;

import java.util.List;

/**
 * @author ntynguyen
 */
public interface OccasionService {
    public List<OccasionDTO> findAllOccasion();

    OccasionDTO getOccasionDTOById(Long occasionId);
}
