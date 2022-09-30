const { createApp } = Vue

createApp({
    data() {
        return {
            client: {},
            bills: {},
            currentBill: {},
            productsToDisplay: [],
            // AVATAR 
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
                    this.loadProductsToDisplay()
                    console.log(this.productsToDisplay)
                })
        },

        loadProductsToDisplay() {
            this.currentBill.client_orders.forEach(clientOrder => {
                clientOrder.ordered_productDTOS.forEach(ordered_productDTO => {
                    this.productsToDisplay.push(ordered_productDTO)
                })
            });
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
        emptyCart() {
            axios.patch('/api/bill/empty/',`billId=${this.currentBill.id}`)
                .then(() => window.location.reload())
        },

        remoteItemFromCart(itemToRemove){
            axios.patch('/api/ordered_product/remove',`orderedProductId=${itemToRemove.id}&billId=${this.currentBill.id}`)
            .then(response => {
                console.log(response)
                window.location.reload();
            }
            )
            .catch(error => console.log(error.response))
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

        // AVATAR 
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

        // FIN DEL AVATAR 


    },

    computed: {},

}).mount('#app')