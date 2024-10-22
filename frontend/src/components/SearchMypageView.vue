<template>

    <v-data-table
        :headers="headers"
        :items="searchMypage"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'SearchMypageView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            searchMypage : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/searchMypages'))

            temp.data._embedded.searchMypages.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.searchMypage = temp.data._embedded.searchMypages;
        },
        methods: {
        }
    }
</script>

