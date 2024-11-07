package com.mongodbvehiculosgabo.app.entity;
	
	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;
	@Document(collection = "vehiculo")
	public class Vehiculo {
		@Id
		private String id;

		private String marca;
		private String modelo;
		private String año;
		private String estado;
		private String precioPorDia;


		public Vehiculo() {
			super();
		}

		public Vehiculo(String id, String marca, String modelo, String año, String estado,
				String precioPorDia) {
			super();
			this.id = id;

		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public String getAño() {
			return año;
		}

		public void setAño(String año) {
			this.año = año;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public String getPrecioPorDia() {
			return precioPorDia;
		}

		public void setPrecioPorDia(String precioPorDia) {
			this.precioPorDia = precioPorDia;
		}
	
	
}
