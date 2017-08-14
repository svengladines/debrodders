package be.occam.debrodders.domain.repository;

public interface JsonObjectRepository {
	
	public JsonObject findOneByUuid( String uuid );

}
