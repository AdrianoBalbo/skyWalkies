const { createApp } = Vue

createApp({
    data() {
        return {
            queryString: "",
            params: "",
            id: "",
            account: {},
            transactions: [],
            client: {},
            fromDate: "",
            toDate: ""
        }
    },

    created() {
        this.queryString = location.search
        this.params = new URLSearchParams(this.queryString)
        this.id = this.params.get("id")
        this.verify()
    },

    mounted() { },

    methods: {

        verify() {
            axios.get(`/api/clients/verify/${this.id}`)
                .then(console.log("si"))
        },

        logout() {
            axios.post('/api/logout')
                .then(response => window.location.href = "./index.html")
        },


    },

    computed: {},

}).mount('#app')