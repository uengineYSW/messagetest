<template>
    <v-card outlined>
        <v-card-title>
            CreateItemCode
        </v-card-title>

        <v-card-text>
            <Number label="Id" v-model="value.id" :editMode="editMode"/>
            <String label="ItemCode" v-model="value.itemCode" :editMode="editMode"/>
            <String label="CodeNo" v-model="value.codeNo" :editMode="editMode"/>
            <String label="Code" v-model="value.code" :editMode="editMode"/>
            <String label="CodeName" v-model="value.codeName" :editMode="editMode"/>
            <String label="IsSys" v-model="value.isSys" :editMode="editMode"/>
            <String label="IsUse" v-model="value.isUse" :editMode="editMode"/>
            <String label="Etc" v-model="value.etc" :editMode="editMode"/>
            <String label="Etc1" v-model="value.etc1" :editMode="editMode"/>
            <String label="Etc2" v-model="value.etc2" :editMode="editMode"/>
            <String label="Etc3" v-model="value.etc3" :editMode="editMode"/>
            <String label="Etc4" v-model="value.etc4" :editMode="editMode"/>
            <String label="Etc5" v-model="value.etc5" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="createItemCode"
            >
                CreateItemCode
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'CreateItemCodeCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.id = 0;
            this.value.itemCode = '';
            this.value.codeNo = '';
            this.value.code = '';
            this.value.codeName = '';
            this.value.isSys = '';
            this.value.isUse = '';
            this.value.etc = '';
            this.value.etc1 = '';
            this.value.etc2 = '';
            this.value.etc3 = '';
            this.value.etc4 = '';
            this.value.etc5 = '';
        },
        watch: {
        },
        methods: {
            createItemCode() {
                this.$emit('createItemCode', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

