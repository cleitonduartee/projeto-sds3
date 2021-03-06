package com.cleitonduarte.dsvendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cleitonduarte.dsvendas.dto.SaleDTO;
import com.cleitonduarte.dsvendas.dto.SaleSuccessDTO;
import com.cleitonduarte.dsvendas.dto.SaleSumDTO;
import com.cleitonduarte.dsvendas.entities.Sale;
import com.cleitonduarte.dsvendas.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	@Autowired
	private SellerService sellerservice;
	
	@Transactional(readOnly = true)
	public Page<SaleDTO> findAll(Pageable pageable){
		sellerservice.findAll();
		Page<Sale> listSeller =  repository.findAll(pageable);
		return listSeller.map(x -> new SaleDTO(x));		 
	}
	@Transactional(readOnly = true)
	public List<SaleSumDTO> amountGroupedBySeller(){
		return repository.amountGroupedBySeller();
	}
	
	@Transactional(readOnly = true)
	public List<SaleSuccessDTO> successGroupedBySeller(){
		return repository.successGroupedBySeller();
	}
}
