const express = require('express');
const router = express.Router();

const productsController = require("../controllers/ProductController");
const cateloryController = require('../controllers/CategoryController');

const bodyparser = require('body-parser');
let urlencodeParser = bodyparser.urlencoded({extended:false});



router.get('/', (request, respon) => {
    respon.render('Login');
});

router.get('/loginProcess', (request, respon) => {
    let un = request.query.txtUN;
    let pw = request.query.txtPW;
    if (un == "vanque" &&  pw == "123") {
        respon.render('index');
    } else {
        respon.render('Login');
        
    }

});

router.get('/index', (request, respon) => {
  
    respon.render('index');
});
router.get('', (request, respon) => {
    respon.render('Product');
});



router.get('/Notification', (request, respon) => {
    respon.render('Notification');
});
router.get('/Profile', (request, respon) => {
    respon.render('Profile');
});


router.get('/Categories', cateloryController.getAll);
router.post('/InsertCatelory', urlencodeParser, cateloryController.insert);
router.post('/DeleteCatelory', urlencodeParser, cateloryController.delete);
router.post('/UpdateCatelory', urlencodeParser, cateloryController.update);









router.get('/Product', productsController.getAlldata);
router.post('/DeleteProduct', urlencodeParser, productsController.delete);
router.post('/UpdateProduct', urlencodeParser, productsController.update);
router.post('/InsertProduct', urlencodeParser, productsController.insert);

router.get('/Categories', (request, respon) => {
    respon.render('Categories');
});
module.exports = router;

