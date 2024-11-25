package com.bothBEandFE.ecom.Services.FAQ;

import com.bothBEandFE.ecom.Dto.FAQDto;
import com.bothBEandFE.ecom.Entity.FAQ;
import com.bothBEandFE.ecom.Entity.Product;
import com.bothBEandFE.ecom.Repository.FAQRepository;
import com.bothBEandFE.ecom.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements  FAQService{

    @Autowired
    private FAQRepository faqRepository;

    @Autowired
    private ProductRepository productRepository;

    public FAQDto postFAQ(Long productId, FAQDto faqDto){
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            FAQ faq= new FAQ();

            faq.setQuestion(faq.getQuestion());
            faq.setAnswer(faq.getAnswer());
            faq.setProduct(optionalProduct.get());

            return faqRepository.save(faq).getFAQDto();
        }
        return null;
    }
}
