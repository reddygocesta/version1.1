package com.sensiple.contactsrepository.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericServiceImpl<T, D, ID extends Serializable> implements GenericService<T, D, ID> {

   /* @Autowired
    private JpaRepository<T, ID> repository;*/

    @Autowired
    private DozerBeanMapper mapper;

    protected Class<T> entityClass;

    protected Class<D> dtoClass;

    @SuppressWarnings("unchecked")
    public GenericServiceImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
        this.dtoClass = (Class<D>) genericSuperclass.getActualTypeArguments()[1];
    }

	@Override
	public D findOne(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<D> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(D dto) {
		// TODO Auto-generated method stub
		
	}

  /*  public D findOne(ID id) {
        return mapper.map(repository.findOne(id), dtoClass);
    }

    public List<D> findAll() {
        List<D> result = new ArrayList<D>();
        for (T t : repository.findAll()) {
            result.add(mapper.map(t, dtoClass));
        }
        return result;
    }
    
    public void save(D dto) {
        repository.saveAndFlush(mapper.map(dto, entityClass));
    }*/

}
