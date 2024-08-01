<template>
    <v-card outlined>
        <v-card-title>
            ItemCode # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="ItemCode" v-model="item.itemCode" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CodeNo" v-model="item.codeNo" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Code" v-model="item.code" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CodeName" v-model="item.codeName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="IsSys" v-model="item.isSys" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="IsUse" v-model="item.isUse" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Etc" v-model="item.etc" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Etc1" v-model="item.etc1" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Etc2" v-model="item.etc2" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Etc3" v-model="item.etc3" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Etc4" v-model="item.etc4" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Etc5" v-model="item.etc5" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>

        <v-card-actions>
            <v-btn text color="deep-purple lighten-2" large @click="goList">List</v-btn>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode"
            >
                Cancel
            </v-btn>
        </v-card-actions>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'ItemCodeServiceItemCodeDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
            editMode: false,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/itemCodes/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
            goList() {
                var path = window.location.href.slice(window.location.href.indexOf("/#/"), window.location.href.lastIndexOf("/#"));
                path = path.replace("/#/", "/");
                this.$router.push(path);
            },
            edit() {
                this.editMode = true;
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.item._links.self.href))
                    }

                    this.editMode = false;

                    this.$emit('input', this.item);
                    this.$emit('delete', this.item);

                } catch(e) {
                    console.log(e)
                }
            },
        },
    };
</script>
