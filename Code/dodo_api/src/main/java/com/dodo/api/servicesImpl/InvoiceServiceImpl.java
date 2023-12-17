package com.dodo.api.servicesImpl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IInvoiceService;
import com.dodo.api.dtos.InvoiceDto;
import com.dodo.api.models.Invoice;
import com.dodo.api.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

	@Autowired
	private InvoiceRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//====LOC====
	@Override
	public List<InvoiceDto> findAll() {
		try {
			return modelMapper.map(repository.findAll(), new TypeToken<List<InvoiceDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public InvoiceDto findById(int id) {
		try {		
			return modelMapper.map(repository.findById(id).get(), InvoiceDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(InvoiceDto dto) {
		try {
			Invoice model = modelMapper.map(dto, Invoice.class);
			repository.save(model);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(repository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<InvoiceDto> findByOrderOrderId(int id) {
		try {
			return modelMapper.map(repository.findByOrderOrderId(id), new TypeToken<List<InvoiceDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<InvoiceDto> findByProductProductId(int id) {
		try {
			return modelMapper.map(repository.findByProductProductId(id), new TypeToken<List<InvoiceDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<InvoiceDto> findByProductProductName(String name) {
		try {
			return modelMapper.map(repository.findByProductProductName(name), new TypeToken<List<InvoiceDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<InvoiceDto> findByShopownerOwnerId(int id) {
		try {
			return modelMapper.map(repository.findByShopownerOwnerId(id), new TypeToken<List<InvoiceDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<InvoiceDto> findByShopownerShopName(String name) {
		try {
			return modelMapper.map(repository.findByShopownerShopName(name), new TypeToken<List<InvoiceDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<InvoiceDto> findByUserUserId(int id) {
		try {
			return modelMapper.map(repository.findByUserUserId(id), new TypeToken<List<InvoiceDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<InvoiceDto> findByUserUsernameAndIsPaid(String name, Boolean isPaid) {
		try {
			return modelMapper.map(repository.findByUserUsernameAndIsPaid(name, isPaid), new TypeToken<List<InvoiceDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<InvoiceDto> findByDateRange(Date from, Date to) {
		try {
			return modelMapper.map(repository.findByDateRange(from, to), new TypeToken<List<InvoiceDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@Override
	public List<InvoiceDto> findByShopownerOwnerIdAndIsPaid(int id, boolean isPaid) {
		try {
			return modelMapper.map(repository.findByShopownerOwnerIdAndIsPaid(id, isPaid), new TypeToken<List<InvoiceDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	//====LOC====


	



}
