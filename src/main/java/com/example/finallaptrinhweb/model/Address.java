package com.example.finallaptrinhweb.model;
    public class Address {
        private int id;
        private String address;
        private long phone;
        private long hotline;
        private String email;
        private String timeOpen;
        private String map;

        // Constructors
        public Address() {
        }

        public Address(String address, long phone, long hotline, String email, String timeOpen, String map) {
            this.address = address;
            this.phone = phone;
            this.hotline = hotline;
            this.email = email;
            this.timeOpen = timeOpen;
            this.map = map;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getPhone() {
            return phone;
        }

        public void setPhone(long phone) {
            this.phone = phone;
        }

        public long getHotline() {
            return hotline;
        }

        public void setHotline(long hotline) {
            this.hotline = hotline;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTimeOpen() {
            return timeOpen;
        }

        public void setTimeOpen(String timeOpen) {
            this.timeOpen = timeOpen;
        }

        public String getMap() {
            return map;
        }

        public void setMap(String map) {
            this.map = map;
        }

        // toString method for debugging or logging
        @Override
        public String toString() {
            return "Address{" +
                    "id=" + id +
                    ", address='" + address + '\'' +
                    ", phone=" + phone +
                    ", hotline=" + hotline +
                    ", email='" + email + '\'' +
                    ", timeOpen='" + timeOpen + '\'' +
                    ", map='" + map + '\'' +
                    '}';
        }
    }
