package com.dodo.web.servicesImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IInvoiceService;
import com.dodo.web.models.Invoice;
import com.dodo.web.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

	@Autowired
	private InvoiceRepository repository;
	
	//====LOC====
	@Override
	public List<Invoice> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Invoice findById(int id) {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean save(Invoice invoice) {
		try {
			repository.save(invoice);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			repository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Invoice> findByOrderOrderId(int id) {
		try {
			return repository.findByOrderOrderId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Invoice> findByProductProductId(int id) {
		try {
			return repository.findByProductProductId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Invoice> findByProductProductName(String name) {
		try {
			return repository.findByProductProductName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Invoice> findByShopownerOwnerId(int id) {
		try {
			return repository.findByShopownerOwnerId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Invoice> findByShopownerShopName(String name) {
		try {
			return repository.findByShopownerShopName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Invoice> findByUserUserId(int id) {
		try {
			return repository.findByUserUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Invoice> findByUserUsernameAndIsPaid(String name, Boolean isPaid) {
		try {
			return repository.findByUserUsernameAndIsPaid(name, isPaid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public List<Invoice> findByDateRange(Date from, Date to) {
		try {
			return repository.findByDateRange(from, to);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@Override
	public List<Invoice> findByShopownerOwnerIdAndIsPaid(int id, boolean isPaid) {
		try {
			return repository.findByShopownerOwnerIdAndIsPaid(id, isPaid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	//====LOC====


	



}
