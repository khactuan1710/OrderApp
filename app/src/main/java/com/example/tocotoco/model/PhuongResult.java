package com.example.tocotoco.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhuongResult {
    @SerializedName("exitcode")
    int exitcode;
    @SerializedName("data")
    PhuongResult2 data;



    public class PhuongResult2 {
        @SerializedName("nItems")
        int nItems;
        @SerializedName("nPages")
        int nPages;
        @SerializedName("data")
        List<PhuongResult3> data;

        public class PhuongResult3 {
            @SerializedName("_id")
            String _id;
            @SerializedName("name")
            String name;
            @SerializedName("code")
            String code;
            @SerializedName("parent_code")
            String parent_code;
            @SerializedName("path_with_type")
            String path_with_type;

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

            public String getParent_code() {
                return parent_code;
            }

            public void setParent_code(String parent_code) {
                this.parent_code = parent_code;
            }

            public String getPath_with_type() {
                return path_with_type;
            }

            public void setPath_with_type(String path_with_type) {
                this.path_with_type = path_with_type;
            }
        }

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

        public List<PhuongResult3> getData() {
            return data;
        }

        public void setData(List<PhuongResult3> data) {
            this.data = data;
        }
    }

    public int getExitcode() {
        return exitcode;
    }

    public void setExitcode(int exitcode) {
        this.exitcode = exitcode;
    }

    public PhuongResult2 getData() {
        return data;
    }

    public void setData(PhuongResult2 data) {
        this.data = data;
    }
}
