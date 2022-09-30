const { createApp } = Vue

createApp({
    data() {
        return {
            client: {},
            bills: [],
            currentBill: {},
            cardholder: "",
            cardNumber: "",
            thruDate: new Date(),
            cvv: 0,
            fromAccount: "",
            destinyAccount: "VIN7070",
            //AVATAR
                        avatar: {},
            avatarHead: new Image(),
            avatarBody: new Image(),
            avatarBodyColor: new Image(),
            avatarFace: new Image(),
            avatarShoes: new Image(),
            avatarArtLine: new Image(),


        }
    },

    created() {
        this.loadClientData();

    },

    mounted() { },

    methods: {
        loadClientData() {
            axios.get('/api/clients/current')
                .then(response => {
                    this.client = response.data;
                    this.bills = this.client.bills;
                    this.currentBill = this.bills.find(bill => bill.payed == false)
                    console.log(this.currentBill);
                })
        },



        nameFormater(productName) {
            productName = productName.replace(/-/g, " ")
            productName = productName.replace(/_/g, " ")
            return productName;
        },

        moneyFormatter(numberToFormat) {
            let formatter = new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: 'USD',
            })
            return formatter.format(numberToFormat)
        },












        logout() {
            axios.post('/api/logout')
                .then(response => window.location.href = "./index.html")
        },
        areYouSure() {
            let swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'btn btn-light m-2',
                    cancelButton: 'btn btn-light m-2'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: 'Are you sure?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: '¡Yes!',
                cancelButtonText: '¡Cancel!',
                color: 'white',
                background: 'black',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    this.logout()
                } else if (
                    result.dismiss === Swal.DismissReason.cancel
                ) {
                    swalWithBootstrapButtons.fire({
                        title: 'Great!',
                        text: "Let's keep walking in the sky",
                        icon: 'success',
                        color: 'white',
                        background: 'black',
                        showConfirmButton: false
                    })

                }
            })
        },
        areYouSureEmpty() {
            let swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'btn btn-light m-2',
                    cancelButton: 'btn btn-light m-2'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: 'Are you sure?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: '¡Yes!',
                cancelButtonText: '¡Cancel!',
                color: 'white',
                background: 'black',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    this.emptyCart()
                } else if (
                    result.dismiss === Swal.DismissReason.cancel
                ) {
                    swalWithBootstrapButtons.fire({
                        title: 'Great!',
                        text: "Let's buy it then!",
                        icon: 'success',
                        color: 'white',
                        background: 'black',
                        showConfirmButton: false
                    })

                }
            })
        },

        areYouSurePay() {
            let swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'btn btn-light m-2',
                    cancelButton: 'btn btn-light m-2'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: 'Are you sure?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: '¡Yes!',
                cancelButtonText: '¡Cancel!',
                color: 'white',
                background: 'black',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    this.pay()
                } else if (
                    result.dismiss === Swal.DismissReason.cancel
                ) {
                    swalWithBootstrapButtons.fire({
                        title: 'Great!',
                        text: "Payment cancelled",
                        icon: 'success',
                        color: 'white',
                        background: 'black',
                        showConfirmButton: false
                    })

                }
            })
        },

        pay() {
            axios.post("https://homebankingvrubank.herokuapp.com/api/transactions/payment", {
                cardNumber: this.cardNumber,
                cardCvv: this.cvv,
                amount: this.currentBill.total,
                description: 'Purchase in SKYWALKIES',
                thruDate: this.thruDate,
                cardHolder: this.cardHolder,
                accountNumber: this.fromAccount,
                accountNumberTo: this.destinyAccount
            })
                .then(response => {
                    console.log(response)
                    axios.patch('/api/bills/payment', `idBill=${this.currentBill.id}`)
                        .then(response =>{
                            console.log(response,'Purchase completed successfully!')
                            
                            Swal.fire({
                                icon: 'success',
                                title: 'Purchase completed!',
                                color: 'white',
                                background: 'black',    
                                showConfirmButton: false,
                                timer: 1500
                            })
                            setTimeout(()=> {
                                window.location.href=`/api/bills/pdf/download?billId=${this.currentBill.id}`;}
                                , 1500);
                        })
                        .catch(error => console.log(error))
                })
        }, 

        prepareAvatarParts() {
            this.avatarHead.src = "../assets/img/avatarCollection/head" + this.client.avatar.head + ".png";
            this.avatarBody.src = "../assets/img/avatarCollection/body" + this.client.avatar.body + ".png";
            this.avatarBodyColor.src = "../assets/img/avatarCollection/bodyColor" + this.client.avatar.bodyColor + ".png";
            this.avatarFace.src = "../assets/img/avatarCollection/face" + this.client.avatar.face + ".png";
            this.avatarShoes.src = "../assets/img/avatarCollection/shoes" + this.client.avatar.shoes + ".png";
            this.avatarArtLine.src = "../assets/img/avatarCollection/lineArtObligatory.png";
        },

        drawAvatar() {
            let canvas = document.getElementById("myCanvas");
            let ctx = canvas.getContext("2d");
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(this.avatarBodyColor, 0, 0);
            ctx.drawImage(this.avatarFace, 0, 0);
            ctx.drawImage(this.avatarShoes, 0, 0);
            ctx.drawImage(this.avatarBody, 0, 0);
            ctx.drawImage(this.avatarArtLine, 0, 0);
            ctx.drawImage(this.avatarHead, 0, 0);
        },

        renderAvatar() {
            this.prepareAvatarParts()
            this.drawAvatar()
        },

    },

    computed: {},

}).mount('#app')