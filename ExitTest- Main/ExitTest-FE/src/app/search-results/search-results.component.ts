import { Component, Input, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'product.interface';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.scss']
})
export class SearchResultsComponent implements OnInit {
  products: Product[] = [];
  searchParam: string = '';
  searchQuery: string = '';
  @Input() openProductDetails!: (product: Product) => void;

  constructor(private productService: ProductService, private sanitizer: DomSanitizer, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.searchParam = params['searchParam'];
      this.searchQuery = params['searchQuery'];

      this.searchProducts();
    });
  }

  searchProducts(): void {
    const encodedQuery = encodeURIComponent(this.searchQuery);
    // Call the product service to get the search results based on the encoded search query
    this.productService.getProductsBySearch(encodedQuery).subscribe(
      (response: Product[]) => {
        if (this.searchQuery) {
          this.products = response.filter(product =>
            product.productName.toLowerCase().includes(this.searchQuery.toLowerCase())
          );
          console.log(response);
        } else {
          Swal.fire('Invalid Input',"",'info')
          this.router.navigate(['/']);
        }
      },
      (error) => {
        console.error('API Error:', error);
      }
    );
  }
  
  getProductImageUrl(base64String: string): SafeUrl {
    const dataUrl = 'data:image/jpeg;base64,' + base64String;
    return this.sanitizer.bypassSecurityTrustUrl(dataUrl);
  }
}
