package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import storysflower.com.storysflower.dto.OccasionDTO;

import java.util.List;

import static storysflower.com.storysflower.model.tables.tables.Occasion.OCCASION;

/**
 * @author ntynguyen
 */
@Repository
public class OccasionRepository {
    @Autowired
    private DSLContext dslContext;

    public List<OccasionDTO> findAllOccasion() {
        return dslContext.selectFrom(OCCASION)
                .fetchInto(OccasionDTO.class);
    }

    public OccasionDTO getOccasionDTOById(Long occasionId) {
        return dslContext.selectFrom(OCCASION)
                .where(OCCASION.OCCASION_ID.eq(occasionId))
                .fetchOneInto(OccasionDTO.class);
    }
}
