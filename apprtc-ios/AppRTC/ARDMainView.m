/*
 *  Copyright 2015 The WebRTC Project Authors. All rights reserved.
 *
 *  Use of this source code is governed by a BSD-style license
 *  that can be found in the LICENSE file in the root of the source
 *  tree. An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */

#import "ARDMainView.h"

#import "UIImage+ARDUtilities.h"

static CGFloat const kRoomTextFieldHeight = 40;
static CGFloat const kRoomTextFieldMargin = 8;
static CGFloat const kCallControlMargin = 16;

// Helper view that contains a text field and a clear button.
@interface ARDRoomTextField : UIView <UITextFieldDelegate>
@property(nonatomic, readonly) NSString *roomText;
@end

@implementation ARDRoomTextField {
  UITextField *_roomText;
}

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    _roomText = [[UITextField alloc] initWithFrame:CGRectZero];
    _roomText.borderStyle = UITextBorderStyleNone;
    _roomText.font = [UIFont systemFontOfSize:12];
    _roomText.placeholder = @"Room name";
    _roomText.autocorrectionType = UITextAutocorrectionTypeNo;
    _roomText.autocapitalizationType = UITextAutocapitalizationTypeNone;
    _roomText.clearButtonMode = UITextFieldViewModeAlways;
    _roomText.delegate = self;
    _roomText.text = @"1234567";
    _roomText.textColor = UIColor.whiteColor;
    [self addSubview:_roomText];

    // Give rounded corners and a light gray border.
    self.layer.borderWidth = 1;
    self.layer.borderColor = [[UIColor lightGrayColor] CGColor];
    self.layer.cornerRadius = 2;
  }
  return self;
}

- (void)layoutSubviews {
  _roomText.frame =
      CGRectMake(kRoomTextFieldMargin, 0, CGRectGetWidth(self.bounds) - kRoomTextFieldMargin,
                 kRoomTextFieldHeight);
}

- (CGSize)sizeThatFits:(CGSize)size {
  size.height = kRoomTextFieldHeight;
  return size;
}

- (NSString *)roomText {
  return _roomText.text;
}

#pragma mark - UITextFieldDelegate

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
  // There is no other control that can take focus, so manually resign focus
  // when return (Join) is pressed to trigger |textFieldDidEndEditing|.
  [textField resignFirstResponder];
  return YES;
}

@end

@implementation ARDMainView {
  ARDRoomTextField *_roomText;
  UIButton *_startRegularCallButton;
}

@synthesize delegate = _delegate;

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    self.backgroundColor = [UIColor colorWithRed:33.0/255.0 green:33.0/255.0 blue:33.0/255.0 alpha:1.0];
    _roomText = [[ARDRoomTextField alloc] initWithFrame:CGRectZero];
    
    [self addSubview:_roomText];

    UIFont *controlFont = [UIFont boldSystemFontOfSize:18.0];
    UIColor *controlFontColor = [UIColor whiteColor];

    _startRegularCallButton = [UIButton buttonWithType:UIButtonTypeRoundedRect];

    _startRegularCallButton.titleLabel.font = controlFont;
    [_startRegularCallButton setTitleColor:controlFontColor forState:UIControlStateNormal];
//    _startRegularCallButton.backgroundColor
//        = [UIColor colorWithRed:66.0/255.0 green:200.0/255.0 blue:90.0/255.0 alpha:1.0];
    [_startRegularCallButton setTitle:@"Join room" forState:UIControlStateNormal];
    [_startRegularCallButton addTarget:self
                                action:@selector(onStartRegularCall:)
                      forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:_startRegularCallButton];
    //self.backgroundColor = [UIColor whiteColor];
  }
  return self;
}


- (void)layoutSubviews {
  CGRect bounds = self.bounds;
  CGFloat roomTextWidth = bounds.size.width - 2 * kRoomTextFieldMargin;
  CGFloat roomTextHeight = [_roomText sizeThatFits:bounds.size].height;
  _roomText.frame =
      CGRectMake(kRoomTextFieldMargin, kRoomTextFieldMargin, roomTextWidth,
                 roomTextHeight);
  _roomText.center = CGPointMake(CGRectGetWidth(self.bounds)/2,CGRectGetHeight(self.bounds)/2 - 100);
  CGFloat buttonHeight = 60;
  CGFloat regularCallFrameTop = CGRectGetMaxY(_roomText.frame) + kCallControlMargin;
  CGRect regularCallFrame = CGRectMake(kCallControlMargin,
                                       regularCallFrameTop,
                                       bounds.size.width - 2*kCallControlMargin,
                                       buttonHeight);
   _startRegularCallButton.frame = regularCallFrame;
}

#pragma mark - Private

//- (void)updateAudioLoopButton {
//  if (_isAudioLoopPlaying) {
//    [_audioLoopButton setTitle:@"Stop sound" forState:UIControlStateNormal];
//  } else {
//    [_audioLoopButton setTitle:@"Play sound" forState:UIControlStateNormal];
//  }
//}

//- (void)onToggleAudioLoop:(id)sender {
//  [_delegate mainViewDidToggleAudioLoop:self];
//}

- (void)onStartRegularCall:(id)sender {
  [_delegate mainView:self didInputRoom:_roomText.roomText isLoopback:NO];
}

//- (void)onStartLoopbackCall:(id)sender {
//  [_delegate mainView:self didInputRoom:_roomText.roomText isLoopback:YES];
//}

@end
