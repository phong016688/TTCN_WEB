package storysflower.com.storysflower.services;

import storysflower.com.storysflower.dto.RevenueDTO;

import java.util.List;

public interface RevenueService {
    public List<RevenueDTO> findAllRevenue();

    public RevenueDTO findRevenueDTOByDate(String date);
}
