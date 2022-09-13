package pratica_04;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder( {"kindOf", "name", "year", "elementId"} )
public class ElementObject {

	private UUID elementId;
	private String kindOf;
	private String name;
	private String year;

	public ElementObject( ) {
	}
	
	public ElementObject(String kind) {
		this.kindOf = kind;
		this.elementId = UUID.randomUUID();
	}

	@Override
	public String toString() {
		return "Tipo de Objeto: " + kindOf 
			+ " | Nome: " + name 
			+ " | Release Date: " + year 
			+ " | Id único: " + elementId;
	}

	public String getKind() {
		return this.kindOf;
	}

	public void setKind(String kind) {
		this.kindOf = kind;
	}

	public UUID getElementId() {
		return this.elementId;
	}

	public void resetElementId() {
		this.elementId = UUID.randomUUID();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String elemName) {
		this.name = elemName;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(int elemYear) {
		String yr = "";
		yr = Integer.toString(elemYear);
		this.year = yr;
	}

}
