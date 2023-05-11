package com.ucompensar.facialrecognition.view.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.ucompensar.facialrecognition.business.dto.faceuser.AttributesFaceDto;
import com.ucompensar.facialrecognition.business.service.AttributeFaceService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ViewScoped
@Named
public class AuditController implements Serializable {

   @Autowired
   private AttributeFaceService attributeFaceService;

   @Getter
   @Setter
   private LazyDataModel<AttributesFaceDto> listFaces;

   @PostConstruct
   public void init() {
      listFaces = new LazyDataModel<>() {

         @Override
         public AttributesFaceDto getRowData(String rowKey) {
            for (AttributesFaceDto customer : listFaces) {
               if (customer.getId().equals(rowKey)) {
                  return customer;
               }
            }
            return null;
         }

         @Override
         public int count(Map<String, FilterMeta> map) {
            this.setRowCount(attributeFaceService.getAllPaginatorCount());
            return this.getRowCount();
         }

         @Override
         public String getRowKey(AttributesFaceDto attributesFaceDto) {
            return attributesFaceDto.getId();
         }

         @Override
         public List<AttributesFaceDto> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
            final PageRequest pageRequest = PageRequest.of(offset == NumberUtils.INTEGER_ZERO ? offset : offset / pageSize, pageSize);
            final List<AttributesFaceDto> listDataTable = attributeFaceService.getAllPaginator(pageRequest);
            this.setPageSize(pageSize);
            return listDataTable;
         }
      };
   }

}
