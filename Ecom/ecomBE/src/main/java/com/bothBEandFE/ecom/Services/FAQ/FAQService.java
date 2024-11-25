package com.bothBEandFE.ecom.Services.FAQ;

import com.bothBEandFE.ecom.Dto.FAQDto;

public interface FAQService {

    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
