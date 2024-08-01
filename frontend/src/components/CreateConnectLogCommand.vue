<template>
    <v-card outlined>
        <v-card-title>
            CreateConnectLog
        </v-card-title>

        <v-card-text>
            <Number label="LogNo" v-model="value.logNo" :editMode="editMode"/>
            <Number label="LoginDt" v-model="value.loginDt" :editMode="editMode"/>
            <String label="LoginId" v-model="value.loginId" :editMode="editMode"/>
            <String label="LoginIdnm" v-model="value.loginIdnm" :editMode="editMode"/>
            <String label="AccessIp" v-model="value.accessIp" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="createConnectLog"
            >
                CreateConnectLog
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
        name: 'CreateConnectLogCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.logNo = 0;
            this.value.loginDt = 0;
            this.value.loginId = '';
            this.value.loginIdnm = '';
            this.value.accessIp = '';
        },
        watch: {
        },
        methods: {
            createConnectLog() {
                this.$emit('createConnectLog', this.value);
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

