package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.Basket;
import com.project.domain.leadtime;
import com.project.persistence.BasketRepository;
import com.project.persistence.LeadtimeRepository;

@Service
public class DataServiceImpl implements DataService{
	
	@Autowired
	private LeadtimeRepository leadRepo;
	
	@Autowired
	private BasketRepository basketRepo;
	
	public List<leadtime> getData(){
		return leadRepo.findAll();
	}
	
	public List<leadtime> getResult(String[] search){
		if (search[0].equals("부품대분류")) return leadRepo.findByMachineryContaining(search[1]);
		else if (search[0].equals("부품명")) return leadRepo.findByItemsContaining(search[1]);
		else if (search[0].equals("부품번호")) 	return leadRepo.findByPart1Containing(search[1]);
		else return leadRepo.findByBaljucheoContaining(search[1]);		
	}
	//String끼리 비교는 equals 제발 기억하자
	
	public void addBasket(Basket[] basket) {
		for (Basket e: basket) {
			basketRepo.save(e);
		}		
	}
	
	public List<Basket> getBasket(){
		return basketRepo.findAll();
		//로그인 구현되면 쿼리메소드 findByUsernameOrderByCategory 정도 하면 될듯
		//또는 리액트에서 sort 구현 가능
	}
	
	@Transactional
	public void delBasket(int[] idNum) {
		for (int e: idNum) {
			basketRepo.deleteById(e);
		}	
	}

}
