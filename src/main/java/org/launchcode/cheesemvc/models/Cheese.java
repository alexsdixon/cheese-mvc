package org.launchcode.cheesemvc.models;
import java.util.ArrayList;


    public class Cheese {

        private String name;
        private String description;


        // constructor with name only
        public Cheese(String name) {
            this.name = name;

        }

        // constructor with name and description
        public Cheese(String name, String description) {
            this.name = name;
            this.description = description;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
