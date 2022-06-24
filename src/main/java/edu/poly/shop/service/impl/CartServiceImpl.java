package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.poly.shop.domain.Order;
import edu.poly.shop.repository.CartRepository;
import edu.poly.shop.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartRepository cartRepository;

	@Override
	public <S extends Order> S save(S entity) {
		return cartRepository.save(entity);
	}

	@Override
	public <S extends Order> Optional<S> findOne(Example<S> example) {
		return cartRepository.findOne(example);
	}

	@Override
	public List<Order> findAll() {
		return cartRepository.findAll();
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return cartRepository.findAll(pageable);
	}

	@Override
	public List<Order> findAll(Sort sort) {
		return cartRepository.findAll(sort);
	}

	@Override
	public List<Order> findAllById(Iterable<Long> ids) {
		return cartRepository.findAllById(ids);
	}

	@Override
	public Optional<Order> findById(Long id) {
		return cartRepository.findById(id);
	}

	@Override
	public <S extends Order> List<S> saveAll(Iterable<S> entities) {
		return cartRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		cartRepository.flush();
	}

	@Override
	public <S extends Order> S saveAndFlush(S entity) {
		return cartRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return cartRepository.existsById(id);
	}

	@Override
	public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
		return cartRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
		return cartRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Order> entities) {
		cartRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Order> long count(Example<S> example) {
		return cartRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Order> entities) {
		cartRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return cartRepository.count();
	}

	@Override
	public <S extends Order> boolean exists(Example<S> example) {
		return cartRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		cartRepository.deleteById(id);
	}
	
	

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		cartRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Order entity) {
		cartRepository.delete(entity);
	}

	@Override
	public <S extends Order, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return cartRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		cartRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		cartRepository.deleteAllInBatch();
	}

	@Override
	public Order getOne(Long id) {
		return cartRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Order> entities) {
		cartRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		cartRepository.deleteAll();
	}

	@Override
	public Order getById(Long id) {
		return cartRepository.getById(id);
	}

	@Override
	public <S extends Order> List<S> findAll(Example<S> example) {
		return cartRepository.findAll(example);
	}

	@Override
	public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
		return cartRepository.findAll(example, sort);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
