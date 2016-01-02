package org.bdickele.sptransp.controller.dto;

import lombok.Getter;
import org.bdickele.sptransp.domain.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@Getter
public class RequestWrapperDTO implements Serializable {

    private static final long serialVersionUID = 4651505999077235127L;

    private Long totalNumberOfRequests;

    private int totalNumberOfPages;

    private int numberOfElementsPerPage;

    private int currentPageIndex;

    private int numberOfElementsDisplayed;

    private List<RequestDTO> requests;

    public RequestWrapperDTO(Pageable pageable, Page<Request> page) {
        this.requests = RequestDTO.build(page.getContent(), false);
        this.totalNumberOfPages = page.getTotalPages();
        this.numberOfElementsPerPage = pageable.getPageSize();
        this.currentPageIndex = pageable.getPageNumber();
        this.numberOfElementsDisplayed = page.getNumberOfElements();
        this.totalNumberOfRequests = page.getTotalElements();
    }
}
