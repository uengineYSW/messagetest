<template>
    <div>
        <v-list two-line v-if="list.length > 0">
            <v-list-item-group 
                    v-model="selected" 
                    color="primary"
                    @change="select"
            >
                <v-list-item v-for="(item, idx) in list" :key="idx">
                    <template v-slot:default="{ active }">
                        <v-list-item-avatar color="primary-darker-1">
                        </v-list-item-avatar>
                        
                        <v-list-item-content>
                            <v-list-item-title>
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                ItemCode :  {{item.itemCode }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                CodeNo :  {{item.codeNo }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Code :  {{item.code }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                CodeName :  {{item.codeName }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                IsSys :  {{item.isSys }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                IsUse :  {{item.isUse }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Etc :  {{item.etc }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Etc1 :  {{item.etc1 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Etc2 :  {{item.etc2 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Etc3 :  {{item.etc3 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Etc4 :  {{item.etc4 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Etc5 :  {{item.etc5 }}
                            </v-list-item-subtitle>
                        </v-list-item-content>

                        <v-list-item-action>
                            <v-checkbox :input-value="active" color="primary-darker-1"></v-checkbox>
                        </v-list-item-action>
                    </template>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </div>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'ItemCodeServiceItemCodePicker',
        props: {
            value: [String, Object, Array, Number, Boolean],
        },
        data: () => ({
            list: [],
            selected: null,
        }),
        async created() {
            var me = this;
            var temp = await axios.get(axios.fixUrl('/itemCodes'))
            if(temp.data) {
                me.list = temp.data._embedded.itemCodes;
            }

            if(me.value && typeof me.value == "object" && Object.values(me.value)[0]) {
                var id = Object.values(me.value)[0];
                var tmpValue = await axios.get(axios.fixUrl('/itemCodes/' + id))
                if(tmpValue.data) {
                    var val = tmpValue.data
                    me.list.forEach(function(item, idx) {
                        if(item.name == val.name) {
                            me.selected = idx
                        }
                    })
                }
            }
        },
        methods: {
            select(val) {
                var obj = {}
                if(val != undefined) {
                    var arr = this.list[val]._links.self.href.split('/');
                    obj['id'] = arr[4]; 
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    this.$emit('selected', obj);
                }
            },
        },
    };
</script>

