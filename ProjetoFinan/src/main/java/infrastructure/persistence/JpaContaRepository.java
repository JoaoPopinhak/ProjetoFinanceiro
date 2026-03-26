package infrastructure.persistence;

import java.util.Optional;

import application.repository.ContaRepository;
import domain.conta.Conta;
import infrastructure.entity.ContaEntity;
import infrastructure.mapper.ContaMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;


public class JpaContaRepository implements ContaRepository{
	
	private EntityManagerFactory emf;
	
	public JpaContaRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public Conta salvar(Conta contaDomain) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try{
			tx.begin();
			
			ContaEntity contaEntity = ContaMapper.toEntity(contaDomain);
			
			if(contaEntity.getId() == null) {
				em.persist(contaEntity);
			}else {
				contaEntity = em.merge(contaEntity);
			}
			
			tx.commit();
			
			return ContaMapper.toDomain(contaEntity);
			
		}catch(Exception e) {
			if(tx.isActive()) {
				tx.rollback();
			}
			
			throw new RuntimeException("Erro ao salvar conta", e);
			
		}finally {
			em.close();
		}
	}

	@Override
	public Optional<Conta> BuscarPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		
		try {
			ContaEntity contaEntity = em.find(ContaEntity.class, id);
			
			if(contaEntity == null) {
				return Optional.empty();
			}
			
			Conta contaDomain = ContaMapper.toDomain(contaEntity);
			
			return Optional.of(contaDomain);
			
		}finally {
			em.close();
		}
	}

	@Override
	public void deletar(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			ContaEntity contaEntity = em.find(ContaEntity.class, id);
			
			if(contaEntity != null) {
				em.remove(contaEntity);
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
