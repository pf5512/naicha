package com.naicha.app.mode;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alipayResult")
public class AlipayResult implements Serializable{

	/**
	 * @author yangxujia
	 * @date 2015年10月21日上午11:29:41
	 */
	private static final long serialVersionUID = 7785830136709429570L;
	private String notify_id;
	private Date notify_time;
	private String notify_type;
	private String sign_type;
	private String sign;
	private String out_trade_no;
	private String subject;
	private String payment_type;
	private String trade_no;
	private String trade_state;
	private String seller_id;
	private String seller_email;
	private String buyer_id;
	private String buyer_email;
	private Double total_fee;
	private Integer quantity;
	private Double price;
	private String body;
	private Date gmt_create;
	private Date gmt_payment;
	private String is_total_fee_adjust;
	private String use_coupon;
	private Double discount;
	private String refund_status;
	private Date gmt_refund;
	
	@Id
	@Column(name = "notify_id", unique = true, nullable = false)
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	
	@Column(name = "notify_time", nullable = false)
	public Date getNotify_time() {
		return notify_time;
	}
	public void setNotify_time(Date notify_time) {
		this.notify_time = notify_time;
	}
	
	@Column(name = "notify_type", nullable = false)
	public String getNotify_type() {
		return notify_type;
	}
	
	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	@Column(name = "sign_type", nullable = false)
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
	@Column(name = "sign", nullable = false)
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	@Column(name = "out_trade_no", nullable = false)
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	@Column(name = "subject", nullable = false)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name = "payment_type", nullable = false)
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	
	@Column(name = "trade_no", nullable = false)
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	
	@Column(name = "trade_state", nullable = false)
	public String getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}
	
	@Column(name = "seller_id", nullable = false)
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	
	@Column(name = "seller_email", nullable = false)
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
	
	@Column(name = "buyer_id", nullable = false)
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	
	@Column(name = "buyer_email", nullable = false)
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	
	@Column(name = "total_fee", nullable = false)
	public Double getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Double total_fee) {
		this.total_fee = total_fee;
	}
	
	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Column(name = "price", nullable = false)
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name = "body", nullable = false)
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Column(name = "gmt_create", nullable = false)
	public Date getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}
	
	@Column(name = "gmt_payment", nullable = false)
	public Date getGmt_payment() {
		return gmt_payment;
	}
	public void setGmt_payment(Date gmt_payment) {
		this.gmt_payment = gmt_payment;
	}
	
	@Column(name = "is_total_fee_adjust", nullable = false)
	public String getIs_total_fee_adjust() {
		return is_total_fee_adjust;
	}
	public void setIs_total_fee_adjust(String is_total_fee_adjust) {
		this.is_total_fee_adjust = is_total_fee_adjust;
	}
	
	@Column(name = "use_coupon", nullable = false)
	public String getUse_coupon() {
		return use_coupon;
	}
	public void setUse_coupon(String use_coupon) {
		this.use_coupon = use_coupon;
	}
	
	@Column(name = "discount", nullable = false)
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	@Column(name = "refund_status", nullable = false)
	public String getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
	
	@Column(name = "gmt_refund", nullable = false)
	public Date getGmt_refund() {
		return gmt_refund;
	}
	public void setGmt_refund(Date gmt_refund) {
		this.gmt_refund = gmt_refund;
	}
	
	
}
