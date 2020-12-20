package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.ProductRevenueDTO;
import storysflower.com.storysflower.dto.RevenueDTO;
import storysflower.com.storysflower.repositories.RevenueRepository;
import storysflower.com.storysflower.services.RevenueService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class RevenueServiceImpls implements RevenueService {

    @Autowired
    RevenueRepository revenueRepository;

    @Override
    public List<RevenueDTO> findAllRevenue() {

        List<RevenueDTO> listRevenue = new ArrayList<>();
        String pattern = "MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        List<ProductRevenueDTO> list = revenueRepository.findAllProductRevenue();
        List<Date> l = new ArrayList<>();
        for (ProductRevenueDTO p : list) {
            l.add(p.getDate());
        }
        if (list.size() > 0) {
            List<String> listDate = new ArrayList<>();
            listDate.add(simpleDateFormat.format(l.get(0)));
            for (int i = 1; i < l.size(); i++) {
                Date d = l.get(i);
                String date = simpleDateFormat.format(d);
                boolean check = true;
                if (l.size() != 1) {
                    for (int j = 0; j < listDate.size(); j++) {
                        if (date.equals(listDate.get(j))) {
                            check = false;
                            break;
                        }
                    }
                    if (check) listDate.add(date);
                } else {
                    listDate.add(date);
                }
            }
            List<ProductRevenueDTO> listProduct = new ArrayList<>();
            listProduct.add(list.get(0));
            listProduct.get(0).setDateStr(simpleDateFormat.format(listProduct.get(0).getDate()));

            /* thay cho group by Prodctnanme*/
            for (int i = 1; i < list.size(); i++) {
                boolean check = true;
                int index = 0;
                list.get(i).setDateStr(simpleDateFormat.format(list.get(i).getDate()));
                for (int j = 0; j < listProduct.size(); j++) {
                    if (list.get(i).getName().equals(listProduct.get(j).getName()) && list.get(i).getDateStr().equals(listProduct.get(j).getDateStr())) {
                        index = j;
                        check = false;
                        break;
                    }
                }
                if (check) {
                    listProduct.add(list.get(i));
                } else {
                    listProduct.get(index).setQuatity(listProduct.get(index).getQuatity() + list.get(i).getQuatity());
                }
            }
            for (int i = 0; i < listDate.size(); i++) {
                List<ProductRevenueDTO> listProductByCart = new ArrayList<>();
                for (int j = 0; j < listProduct.size(); j++) {
                    if (listDate.get(i).equals(listProduct.get(j).getDateStr()))
                        listProductByCart.add(listProduct.get(j));
                }
                RevenueDTO revenueDTO = new RevenueDTO(i + 1, listDate.get(i), listProductByCart);
                listRevenue.add(revenueDTO);
            }
            for (RevenueDTO r : listRevenue) {
                Double money = 0.0;
                for (int i = 0; i < r.getListCart().size(); i++) {
                    money += r.getListCart().get(i).getPrice() * r.getListCart().get(i).getQuatity();
                }
                r.setTotalMoney(money);
            }
        }
        return listRevenue;
    }

    @Override
    public RevenueDTO findRevenueDTOByDate(String date) {
        List<RevenueDTO> l = findAllRevenue();
        for (RevenueDTO r : l) {
            if (date.equals(r.getDate())) {
                return r;
            }
        }
        return null;
    }
}
