package br.com.marcelpinotti.camelspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserViaCepDTO {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("lastname")
        private String lastname;
        @JsonProperty("email")
        private String email;
        @JsonProperty("password")
        private String password;
        @JsonProperty("address")
        private ViaCepDTO address;

        public UserViaCepDTO(){}

        public UserViaCepDTO(Long id, String name, String lastname, String email, String password, ViaCepDTO address) {
            this.id = id;
            this.name = name;
            this.lastname = lastname;
            this.email = email;
            this.password = password;
            this.address = address;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public ViaCepDTO getAddress() {
            return address;
        }
        public void setAddress(ViaCepDTO address) {
            this.address = address;
        }
}
