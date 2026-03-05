package infrastructure.persistence;

import java.util.Optional;

import application.repository.ContaBancariaRepository;
import domain.conta.Conta;
import domain.conta.ContaBancaria;
import infrastructure.entity.ContaBancariaEntity;
import infrastructure.mapper.ContaBancariaMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class JpaContaBancariaRepository implements ContaBancariaRepository {

	private EntityManagerFactory emf;
	
	public JpaContaBancariaRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public ContaBancaria salvar(Conta contaBancaria) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try{
			tx.begin();
			
			ContaBancariaEntity contaBancariaEntity = ContaBancariaMapper.toEntity(contaBancaria);
			
			if(contaBancariaEntity.getId() == null) {
				em.persist(contaBancariaEntity);
			}else {
				em.merge(contaBancariaEntity);
			}
			
			tx.commit();

			ContaBancaria contaBancariaDomain = ContaBancariaMapper.toDomain(contaBancariaEntity);
				
			return contaBancariaDomain;
			
		}catch(Exception e1) {
			if(tx.isActive()) {	
				tx.rollback();
			}
			e1.printStackTrace();
			throw e1;
		}finally {
			em.close();
		}
	}

	
	@Override
	public Optional<Conta> BuscarPorId (Long id) {
		
		EntityManager em = emf.createEntityManager();		
		
		try {
			ContaBancariaEntity contaBancariaEntity = em.find(ContaBancariaEntity.class, id);
			
			if(contaBancariaEntity == null) {
				return Optional.empty();
			}
			
			ContaBancaria contaBancariaDomain = ContaBancariaMapper.toDomain(contaBancariaEntity);
			
			return Optional.of(contaBancariaDomain);
					
		}finally{
			em.close();
		}

	}

	@Override
	public void deletar(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			ContaBancariaEntity contaBancariaEntity = em.find(ContaBancariaEntity.class, id);
			
			if(contaBancariaEntity != null) {
				em.remove(contaBancariaEntity);
			}
			
			tx.commit();
			
		}catch(Exception e) {
			if(tx.isActive()) {
				tx.rollback();
			}
		}finally {
			em.close();
		}
	}
}
