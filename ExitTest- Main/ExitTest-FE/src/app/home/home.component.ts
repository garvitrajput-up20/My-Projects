import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Product } from 'product.interface';
import { AuthService } from '../services/auth-service.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ProductDetailsPopupComponent } from '../product-details-popup/product-details-popup.component';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  products: Product[] = [];
  productImages: SafeUrl = [];
  isLoggedIn : boolean = false;
  sortDirection: 'asc' | 'desc' = 'asc';
  searchOption: string = 'name'; // Default search option
  searchQuery: string = '';

  constructor(private productService: ProductService, private sanitizer: DomSanitizer, private authService: AuthService, private dialog: MatDialog, private router: Router) { }


  ngOnInit() {
    this.fetchProducts();
    this.authService.isLoggedIn$.subscribe(isLoggedIn => {
      this.isLoggedIn = isLoggedIn;
    });
  }

  fetchProducts() {
    this.productService.getAllProducts().subscribe(
      (response: Product[]) => {
        this.products = response;
        console.log(response)
        this.productImages = this.products.map(product =>
          this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + product.image));
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  


  openProductDetails(product: Product): void {
    const dialogRef = this.dialog.open(ProductDetailsPopupComponent, {
      data: product
    });
  }


  searchProducts(): void {
    let searchParam: string;
    switch (this.searchOption) {
      case 'name':
        searchParam = 'name';
        break;
      case 'brand':
        searchParam = 'brand';
        break;
      case 'code':
        searchParam = 'productCode';
        break;
      default:
        searchParam = 'name';
        break;
    }
    const searchUrl = `http://localhost:8080/products?${searchParam}=${this.searchQuery}`;
    console.log(searchUrl);
    
    this.router.navigate(['/search-results'], { queryParams: { searchParam: searchParam, searchQuery: this.searchQuery } });
    console.log(searchUrl);
  }
  
}
