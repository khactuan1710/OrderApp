package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResult {
    @SerializedName("exitcode")
    int exitcode;
    @SerializedName("data")
    CityResult2 data;

    public int getExitcode() {
        return exitcode;
    }

    public void setExitcode(int exitcode) {
        this.exitcode = exitcode;
    }

    public CityResult2 getData() {
        return data;
    }

    public void setData(CityResult2 data) {
        this.data = data;
    }

    public class CityResult2 {
        @SerializedName("nItems")
        int nItems;
        @SerializedName("nPages")
        int nPages;
        @SerializedName("data")
        List<CityResult3> data;

        public int getnItems() {
            return nItems;
        }

        public void setnItems(int nItems) {
            this.nItems = nItems;
        }

        public int getnPages() {
            return nPages;
        }

        public void setnPages(int nPages) {
            this.nPages = nPages;
        }

        public List<CityResult3> getData() {
            return data;
        }

        public void setData(List<CityResult3> data) {
            this.data = data;
        }

        public class CityResult3 {
            @SerializedName("_id")
            String _id;
            @SerializedName("name")
            String name;
            @SerializedName("code")
            String code;

            @SerializedName("path_with_type")
            String path_with_type;
            @SerializedName("name_with_type")
            String name_with_type;

            public String getName_with_type() {
                return name_with_type;
            }

            public void setName_with_type(String name_with_type) {
                this.name_with_type = name_with_type;
            }

            public String getPath_with_type() {
                return path_with_type;
            }

            public void setPath_with_type(String path_with_type) {
                this.path_with_type = path_with_type;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
