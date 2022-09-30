const { createApp } = Vue


createApp({
    data() {
        return {
            //CLIENT DATA
            clientEmail: "",
            clientPassword: "",
            newClientFirstName: "",
            newClientLastName: "",
            newClientEmail: "",
            newClientPassword: "",
            confirmNewClientPassword: "",
            currentClient: null,
            currentBill: null,
            // AVATAR
            avatar: {},
            avatarHead: new Image(),
            avatarBody: new Image(),
            avatarBodyColor: new Image(),
            avatarFace: new Image(),
            avatarShoes: new Image(),
            avatarArtLine: new Image(),
            //PRODUCTS DATA
            productsArray: [],
            upperShelf: [],
            middleShelf: [],
            bottomShelf: [],
            renderForModal: "",
            priceToDisplay: "",
            stockToDisplay: "",
            productToDisplay: {},
            shoeColors: [],
            productToDisplaySizes: [],
            shoeSize: "",
            //FILTERS
            selectedColor: [],
            // newUpperShelf:[],
            //ADDTOCART
            productName: "",
            size: 0,
            quantity: 0,

            //BADGE
            badgeNumber:0,

        }
    },
    created() {
        this.loadData()
    },

    beforeMount() {
    },

    mounted() {
    },

    beforeUpdate() {
    },

    updated() {

    },
    methods: {

        loadData() {
            this.loadProducts()
            this.loadClientData()
        },

        loadClientData() {
            axios.get("/api/clients/current")
                .then(response => {
                    this.currentClient = response.data
                    this.avatar = this.currentClient.avatar
                    this.currentBill = this.currentClient.bills.filter(bill => bill.payed == false)
                    console.log(this.currentBill)
                    console.log(this.currentClient.avatar)
                })
                .catch(error => {
                    console.log("Please log in")
                    this.currentClient = null
                })
        },

        // AVATAR

        prepareAvatarParts() {
            this.avatarHead.src = "../assets/img/avatarCollection/head" + this.currentClient.avatar.head + ".png";
            this.avatarBody.src = "../assets/img/avatarCollection/body" + this.currentClient.avatar.body + ".png";
            this.avatarBodyColor.src = "../assets/img/avatarCollection/bodyColor" + this.currentClient.avatar.bodyColor + ".png";
            this.avatarFace.src = "../assets/img/avatarCollection/face" + this.currentClient.avatar.face + ".png";
            this.avatarShoes.src = "../assets/img/avatarCollection/shoes" + this.currentClient.avatar.shoes + ".png";
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



        changeRender(productName) {
            this.renderForModal = productName;
            this.productToDisplay = this.productsArray.find(product => product.name == productName)
            this.productToDisplaySizes = this.productToDisplay.sizes
            console.log(this.productToDisplay.name)

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
        loadProducts() {
            axios.get('/api/products')
                .then(response => {
                    this.productsArray = response.data.filter(product => product.active == true)
                    this.productsArray.forEach(product => product.price = this.moneyFormatter(product.price))
                    // this.priceSortedMaxToMin()
                    // this.priceSortedMinToMax()
                    this.shelvesFiller()
                    this.loadShoeColors(this.productsArray)
                })
        },
        shelvesFiller() {
            this.upperShelf = this.productsArray.slice(0, 14)
            this.middleShelf = this.productsArray.slice(14, 32)
            this.bottomShelf = this.productsArray.slice(32, 47)
        },


        // FILTERS 

        loadShoeColors(shoeArray) {
            this.shoeColors
            shoeArray.forEach(shoe => {
                shoe.shoeColors.forEach(color => {
                    if (!this.shoeColors.includes(color)) {
                        this.shoeColors.push(color)
                    }
                })
            })
        },

        filterByColor() {
            let newUpperShelf = [];
            let newMiddleShelf = [];
            let newBottomShelf = [];

            this.selectedColor.forEach(color => {
                this.productsArray.slice(0, 14).forEach(shoe => {
                    if (shoe.shoeColors.includes(color)) {
                        if (!newUpperShelf.includes(shoe)) {
                            newUpperShelf.push(shoe);
                        }
                    }
                })
            })

            this.selectedColor.forEach(color => {
                this.productsArray.slice(14, 32).forEach(shoe => {
                    if (shoe.shoeColors.includes(color)) {
                        if (!newUpperShelf.includes(shoe)) {
                            newUpperShelf.push(shoe);
                        }
                    }
                })
            })

            this.selectedColor.forEach(color => {
                this.productsArray.slice(32, 47).forEach(shoe => {
                    if (shoe.shoeColors.includes(color)) {
                        if (!newUpperShelf.includes(shoe)) {
                            newUpperShelf.push(shoe);
                        }
                    }
                })
            })

            this.upperShelf = newUpperShelf;
            this.middleShelf = newMiddleShelf;
            this.bottomShelf = newBottomShelf;
        },


        changeSelectedColors(selectedColor) {
            if (this.selectedColor.filter(color => color == selectedColor).length > 0) {

                let indexToRemove = this.selectedColor.indexOf(selectedColor)
                this.selectedColor.splice(indexToRemove, 1)
                console.log(this.selectedColor, 'sacando')


            } else if (this.selectedColor.filter(color => color == selectedColor).length == 0) {
                this.selectedColor.push(selectedColor)
                console.log(this.selectedColor)
            }
            console.log('ejecutando funcion')
        },


        // END FILTERS


        login() {

            axios.post("/api/login", `email=${this.clientEmail}&password=${this.clientPassword}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    Swal.fire({
                        icon: 'success',
                        title: 'Welcome!',
                        color: 'white',
                        background: 'black',    
                        showConfirmButton: false,
                        timer: 1500
                    })
                    setTimeout(()=> {
                        window.location.reload();}
                        , 1500);
                })
                .catch(error => {
                    swal("There was an error with your email or password. Please try again.", {
                        dangerMode: true
                    });
                    console.log("Error:", error.response.status, "Code:", error.code)
                })
            // })

        },

        signUp() {
            axios.post('/api/clients', `firstName=${this.newClientFirstName}&lastName=${this.newClientLastName}&email=${this.newClientEmail}&password=${this.newClientPassword}&confirmPassword=${this.confirmNewClientPassword}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => Swal.fire({
                    icon: 'question',
                    title: 'One more step!',
                    text:'Check your email inbox and verify your account',
                    color: 'white',
                    background: 'black',    
                    showConfirmButton: false,
                    timer: 1500
                }))
                .catch(error => {
                    console.log(error)
                    console.log("Error:", error.response.status, "Code:", error.code, error.response.data)
                    if (error.response.data == "This email belongs to an existing client") {
                        swal(error.response.data, ".", {
                            dangerMode: true
                        })
                    } else {
                        swal("Please fill all the required fields.", {
                            dangerMode: true
                        });
                    }
                })
        },

        logout() {
            axios.post("/api/logout")
                .then(() => {
                    window.location.reload()
                })
        },
        addToCart() {
            axios.post("/api/products/addProduct", [{ id: this.productToDisplay.id, productName: this.productToDisplay.name, size: this.shoeSize, quantity: this.quantity }])
                .then(response =>
                    Swal.fire({
                        icon: 'success',
                        title: 'Shoe added to cart!',
                        showConfirmButton: false,
                        color: 'white',
                        background: 'black',        
                        timer: 1500
                    }))
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
                        timer: 1500,
                        showConfirmButton: false
                    })

                }
            })
        },



    },
    computed: {
        filtering() {
            if (this.selectedColor.length == 0) {
                this.shelvesFiller()
            } else if (this.selectedColor.length > 0) {
                this.filterByColor()
                // console.log('filtering')
            }
        },
        
        // checkBadgeNumber(){
        //     if(this.currentBill!=null){
        //         this.currentBill.client_orders.forEach(clientOrder=>{
        //             clientOrder.ordered_productDTOS.forEach(ordered_productDTOS=>{
        //                 this.badgeNumber = this.badgeNumber+1
        //             })
        //         })
        //     }
        //     return(this.badgeNumber)
        // },

    },
}).mount('#app')

