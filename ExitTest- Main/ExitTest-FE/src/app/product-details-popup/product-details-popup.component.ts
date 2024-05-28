import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductService } from '../services/product.service';
import { Product } from 'product.interface';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-product-details-popup',
  templateUrl: './product-details-popup.component.html',
  styleUrls: ['./product-details-popup.component.scss'],
})
export class ProductDetailsPopupComponent {
  pincode: string = '';

  constructor(
    public dialogRef: MatDialogRef<ProductDetailsPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public product: Product,
    private productService: ProductService,
    private sanitizer: DomSanitizer
  ) { }

  checkDelivery(): void {
    if (this.pincode === null || this.pincode === "") {
      Swal.fire("Invalid Pincode", "Please enter valid pincode", "warning")
      return;
    }
  
    this.productService.checkDeliveryAvailability(this.product.productId, this.pincode).subscribe(
      (response: string) => {
        console.log(this.product);
        console.log(response);
        // Display the delivery_days value in an alert
        Swal.fire('Delivery Available', `Product is deliverable to ${this.pincode} in ${response} days.`, 'success');
      },
      (error) => {
        Swal.fire('Delivery Not Available', `Product is not deliverable to ${this.pincode}.`, 'error');
      }
    );
  }

  getProductImageUrl(base64String: string): SafeUrl {
    const dataUrl = 'data:image/jpeg;base64,' + base64String;
    return this.sanitizer.bypassSecurityTrustUrl(dataUrl);
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
