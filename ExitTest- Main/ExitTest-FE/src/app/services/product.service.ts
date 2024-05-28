import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Product } from 'product.interface';
import { catchError, map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'http://localhost:8080/products';

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }

  getProductsBySearch(searchParam: string): Observable<Product[]> {
    let params = new HttpParams().set('search', searchParam);
    return this.http.get<Product[]>(this.apiUrl, { params });
  }

  checkDeliveryAvailability(productId: number, pincode: string): Observable<string> {
    if(pincode == ""){
      alert("Please enter valid pincode");
      return throwError("Invalid pincode");
    }
    const url = `${this.apiUrl}/${productId}/delivery?pincode=${pincode}`;
    return this.http.get(url, { responseType: 'text' }).pipe(
      map((response: any) => {
        return response as string;
      }),
      catchError((error: any) => {
        let errorMessage = 'An error occurred';
        if (error.error instanceof ErrorEvent) {
          // Client-side error
          errorMessage = error.error.message;
        } else {
          // Server-side error
          errorMessage = error.error;
        }
        return throwError(errorMessage);
      })
    );
  }
}