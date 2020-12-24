package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.RecipientCartDTO;
import storysflower.com.storysflower.repositories.RecipientRepository;
import storysflower.com.storysflower.services.RecipientService;

@Component
public class RecipientServiceImpl implements RecipientService {

    @Autowired
    private RecipientRepository recipientRepository;

    @Override
    public RecipientCartDTO findRecipientCartDTObyIdBuyProduct(Long idProduct) {
        return recipientRepository.getRecipientCartDTObyIdBuyProduct(idProduct);
    }
}
