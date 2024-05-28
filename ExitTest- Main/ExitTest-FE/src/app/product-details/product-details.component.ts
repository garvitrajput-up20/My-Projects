import { Component, Input, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from 'product.interface';
import { AuthService } from '../services/auth-service.service';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})

export class ProductDetailsComponent implements OnInit {
  @Input() product: Product[] = [];
  @Input() openProductDetails!: (product: Product) => void;
  isLoggedIn: boolean = false;
  minPrice: number = 0;
  maxPrice: number | undefined;
  filteredProduct: boolean = false;
  selectedBrand: string = '';
  uniqueBrands: string[] = [];
  filteredProducts: Product[] = [];

  constructor(private productService: ProductService, private AuthService: AuthService, private dialog: MatDialog, private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.productService.getAllProducts().subscribe(
      (product: Product[]) => {
        this.product = product;
        this.uniqueBrands = this.getUniqueBrands(product);
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
    this.AuthService.isLoggedIn$.subscribe(isLoggedIn => {
      this.isLoggedIn = isLoggedIn;
    });
  }


  getUniqueBrands(products: Product[]): string[] {
    const brandsSet = new Set<string>();
    products.forEach((product) => {
      brandsSet.add(product.brand);
    });
    return Array.from(brandsSet);
  }

  applyBrandFilter() {
    this.filteredProducts = this.product.filter((product) => {
      if (this.selectedBrand) {
        return product.brand === this.selectedBrand;
      } else {
        return true; // No brand filter applied, return all products
      }
    });
    this.filteredProduct = this.filteredProducts.length > 0;
  }
  
  getProductImageUrl(base64String: string): SafeUrl {
    const dataUrl = 'data:image/jpeg;base64,' + base64String;
    return this.sanitizer.bypassSecurityTrustUrl(dataUrl);
  }

  checkMaxPrice() {
    if (!this.maxPrice && this.maxPrice !== 0) {
      this.maxPrice = undefined;
      this.applyPriceFilter();
    }
  }

  applyPriceFilter() {
    if (this.minPrice === 0 && this.maxPrice === 0) {
      this.filteredProducts = []; // Empty the filteredProducts array when both fields are 0
    } else {
      this.filteredProducts = this.product.filter((product) => {
        const price = Number(product.price);
        if (this.minPrice && this.maxPrice) {
          return price >= this.minPrice && price <= this.maxPrice;
        } else if (this.minPrice) {
          return price >= this.minPrice;
        } else if (this.maxPrice) {
          return price <= this.maxPrice;
        } else {
          return true; // No price filter applied, return all products
        }
      });
    }
    this.filteredProduct = this.filteredProducts.length > 0 || (this.maxPrice === 0 && this.minPrice !== 0);
  }
}