package storysflower.com.storysflower.services;

import storysflower.com.storysflower.dto.RecipientCartDTO;

public interface RecipientService {
    RecipientCartDTO findRecipientCartDTObyIdBuyProduct(Long idProduct);
}
