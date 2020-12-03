// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Response.proto

package pers.enoch.im.common.generate;

public final class ResponseOuterClass {
  private ResponseOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ResponseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Response)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 type = 1;</code>
     * @return The type.
     */
    int getType();

    /**
     * <code>.MessageResponse message = 2;</code>
     * @return Whether the message field is set.
     */
    boolean hasMessage();
    /**
     * <code>.MessageResponse message = 2;</code>
     * @return The message.
     */
    pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse getMessage();
    /**
     * <code>.MessageResponse message = 2;</code>
     */
    pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponseOrBuilder getMessageOrBuilder();

    /**
     * <code>.UserResponse user = 3;</code>
     * @return Whether the user field is set.
     */
    boolean hasUser();
    /**
     * <code>.UserResponse user = 3;</code>
     * @return The user.
     */
    pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse getUser();
    /**
     * <code>.UserResponse user = 3;</code>
     */
    pers.enoch.im.common.generate.UserResponseOuterClass.UserResponseOrBuilder getUserOrBuilder();

    /**
     * <code>string create_time = 4;</code>
     * @return The createTime.
     */
    java.lang.String getCreateTime();
    /**
     * <code>string create_time = 4;</code>
     * @return The bytes for createTime.
     */
    com.google.protobuf.ByteString
        getCreateTimeBytes();
  }
  /**
   * <pre>
   * 返回实体
   * </pre>
   *
   * Protobuf type {@code Response}
   */
  public static final class Response extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Response)
      ResponseOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Response.newBuilder() to construct.
    private Response(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Response() {
      createTime_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Response();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Response(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              type_ = input.readInt32();
              break;
            }
            case 18: {
              pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse.Builder subBuilder = null;
              if (message_ != null) {
                subBuilder = message_.toBuilder();
              }
              message_ = input.readMessage(pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(message_);
                message_ = subBuilder.buildPartial();
              }

              break;
            }
            case 26: {
              pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse.Builder subBuilder = null;
              if (user_ != null) {
                subBuilder = user_.toBuilder();
              }
              user_ = input.readMessage(pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(user_);
                user_ = subBuilder.buildPartial();
              }

              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              createTime_ = s;
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pers.enoch.im.common.generate.ResponseOuterClass.internal_static_Response_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pers.enoch.im.common.generate.ResponseOuterClass.internal_static_Response_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pers.enoch.im.common.generate.ResponseOuterClass.Response.class, pers.enoch.im.common.generate.ResponseOuterClass.Response.Builder.class);
    }

    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_;
    /**
     * <code>int32 type = 1;</code>
     * @return The type.
     */
    @java.lang.Override
    public int getType() {
      return type_;
    }

    public static final int MESSAGE_FIELD_NUMBER = 2;
    private pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse message_;
    /**
     * <code>.MessageResponse message = 2;</code>
     * @return Whether the message field is set.
     */
    @java.lang.Override
    public boolean hasMessage() {
      return message_ != null;
    }
    /**
     * <code>.MessageResponse message = 2;</code>
     * @return The message.
     */
    @java.lang.Override
    public pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse getMessage() {
      return message_ == null ? pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse.getDefaultInstance() : message_;
    }
    /**
     * <code>.MessageResponse message = 2;</code>
     */
    @java.lang.Override
    public pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponseOrBuilder getMessageOrBuilder() {
      return getMessage();
    }

    public static final int USER_FIELD_NUMBER = 3;
    private pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse user_;
    /**
     * <code>.UserResponse user = 3;</code>
     * @return Whether the user field is set.
     */
    @java.lang.Override
    public boolean hasUser() {
      return user_ != null;
    }
    /**
     * <code>.UserResponse user = 3;</code>
     * @return The user.
     */
    @java.lang.Override
    public pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse getUser() {
      return user_ == null ? pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse.getDefaultInstance() : user_;
    }
    /**
     * <code>.UserResponse user = 3;</code>
     */
    @java.lang.Override
    public pers.enoch.im.common.generate.UserResponseOuterClass.UserResponseOrBuilder getUserOrBuilder() {
      return getUser();
    }

    public static final int CREATE_TIME_FIELD_NUMBER = 4;
    private volatile java.lang.Object createTime_;
    /**
     * <code>string create_time = 4;</code>
     * @return The createTime.
     */
    @java.lang.Override
    public java.lang.String getCreateTime() {
      java.lang.Object ref = createTime_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        createTime_ = s;
        return s;
      }
    }
    /**
     * <code>string create_time = 4;</code>
     * @return The bytes for createTime.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getCreateTimeBytes() {
      java.lang.Object ref = createTime_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        createTime_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (type_ != 0) {
        output.writeInt32(1, type_);
      }
      if (message_ != null) {
        output.writeMessage(2, getMessage());
      }
      if (user_ != null) {
        output.writeMessage(3, getUser());
      }
      if (!getCreateTimeBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, createTime_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (type_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, type_);
      }
      if (message_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, getMessage());
      }
      if (user_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, getUser());
      }
      if (!getCreateTimeBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, createTime_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof pers.enoch.im.common.generate.ResponseOuterClass.Response)) {
        return super.equals(obj);
      }
      pers.enoch.im.common.generate.ResponseOuterClass.Response other = (pers.enoch.im.common.generate.ResponseOuterClass.Response) obj;

      if (getType()
          != other.getType()) return false;
      if (hasMessage() != other.hasMessage()) return false;
      if (hasMessage()) {
        if (!getMessage()
            .equals(other.getMessage())) return false;
      }
      if (hasUser() != other.hasUser()) return false;
      if (hasUser()) {
        if (!getUser()
            .equals(other.getUser())) return false;
      }
      if (!getCreateTime()
          .equals(other.getCreateTime())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
      if (hasMessage()) {
        hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
        hash = (53 * hash) + getMessage().hashCode();
      }
      if (hasUser()) {
        hash = (37 * hash) + USER_FIELD_NUMBER;
        hash = (53 * hash) + getUser().hashCode();
      }
      hash = (37 * hash) + CREATE_TIME_FIELD_NUMBER;
      hash = (53 * hash) + getCreateTime().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static pers.enoch.im.common.generate.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(pers.enoch.im.common.generate.ResponseOuterClass.Response prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * 返回实体
     * </pre>
     *
     * Protobuf type {@code Response}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Response)
        pers.enoch.im.common.generate.ResponseOuterClass.ResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return pers.enoch.im.common.generate.ResponseOuterClass.internal_static_Response_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return pers.enoch.im.common.generate.ResponseOuterClass.internal_static_Response_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                pers.enoch.im.common.generate.ResponseOuterClass.Response.class, pers.enoch.im.common.generate.ResponseOuterClass.Response.Builder.class);
      }

      // Construct using pers.enoch.im.common.generate.ResponseOuterClass.Response.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        type_ = 0;

        if (messageBuilder_ == null) {
          message_ = null;
        } else {
          message_ = null;
          messageBuilder_ = null;
        }
        if (userBuilder_ == null) {
          user_ = null;
        } else {
          user_ = null;
          userBuilder_ = null;
        }
        createTime_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return pers.enoch.im.common.generate.ResponseOuterClass.internal_static_Response_descriptor;
      }

      @java.lang.Override
      public pers.enoch.im.common.generate.ResponseOuterClass.Response getDefaultInstanceForType() {
        return pers.enoch.im.common.generate.ResponseOuterClass.Response.getDefaultInstance();
      }

      @java.lang.Override
      public pers.enoch.im.common.generate.ResponseOuterClass.Response build() {
        pers.enoch.im.common.generate.ResponseOuterClass.Response result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public pers.enoch.im.common.generate.ResponseOuterClass.Response buildPartial() {
        pers.enoch.im.common.generate.ResponseOuterClass.Response result = new pers.enoch.im.common.generate.ResponseOuterClass.Response(this);
        result.type_ = type_;
        if (messageBuilder_ == null) {
          result.message_ = message_;
        } else {
          result.message_ = messageBuilder_.build();
        }
        if (userBuilder_ == null) {
          result.user_ = user_;
        } else {
          result.user_ = userBuilder_.build();
        }
        result.createTime_ = createTime_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof pers.enoch.im.common.generate.ResponseOuterClass.Response) {
          return mergeFrom((pers.enoch.im.common.generate.ResponseOuterClass.Response)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(pers.enoch.im.common.generate.ResponseOuterClass.Response other) {
        if (other == pers.enoch.im.common.generate.ResponseOuterClass.Response.getDefaultInstance()) return this;
        if (other.getType() != 0) {
          setType(other.getType());
        }
        if (other.hasMessage()) {
          mergeMessage(other.getMessage());
        }
        if (other.hasUser()) {
          mergeUser(other.getUser());
        }
        if (!other.getCreateTime().isEmpty()) {
          createTime_ = other.createTime_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        pers.enoch.im.common.generate.ResponseOuterClass.Response parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (pers.enoch.im.common.generate.ResponseOuterClass.Response) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int type_ ;
      /**
       * <code>int32 type = 1;</code>
       * @return The type.
       */
      @java.lang.Override
      public int getType() {
        return type_;
      }
      /**
       * <code>int32 type = 1;</code>
       * @param value The type to set.
       * @return This builder for chaining.
       */
      public Builder setType(int value) {
        
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 type = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearType() {
        
        type_ = 0;
        onChanged();
        return this;
      }

      private pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse message_;
      private com.google.protobuf.SingleFieldBuilderV3<
          pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse, pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse.Builder, pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponseOrBuilder> messageBuilder_;
      /**
       * <code>.MessageResponse message = 2;</code>
       * @return Whether the message field is set.
       */
      public boolean hasMessage() {
        return messageBuilder_ != null || message_ != null;
      }
      /**
       * <code>.MessageResponse message = 2;</code>
       * @return The message.
       */
      public pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse getMessage() {
        if (messageBuilder_ == null) {
          return message_ == null ? pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse.getDefaultInstance() : message_;
        } else {
          return messageBuilder_.getMessage();
        }
      }
      /**
       * <code>.MessageResponse message = 2;</code>
       */
      public Builder setMessage(pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse value) {
        if (messageBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          message_ = value;
          onChanged();
        } else {
          messageBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.MessageResponse message = 2;</code>
       */
      public Builder setMessage(
          pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse.Builder builderForValue) {
        if (messageBuilder_ == null) {
          message_ = builderForValue.build();
          onChanged();
        } else {
          messageBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.MessageResponse message = 2;</code>
       */
      public Builder mergeMessage(pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse value) {
        if (messageBuilder_ == null) {
          if (message_ != null) {
            message_ =
              pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse.newBuilder(message_).mergeFrom(value).buildPartial();
          } else {
            message_ = value;
          }
          onChanged();
        } else {
          messageBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.MessageResponse message = 2;</code>
       */
      public Builder clearMessage() {
        if (messageBuilder_ == null) {
          message_ = null;
          onChanged();
        } else {
          message_ = null;
          messageBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.MessageResponse message = 2;</code>
       */
      public pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse.Builder getMessageBuilder() {
        
        onChanged();
        return getMessageFieldBuilder().getBuilder();
      }
      /**
       * <code>.MessageResponse message = 2;</code>
       */
      public pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponseOrBuilder getMessageOrBuilder() {
        if (messageBuilder_ != null) {
          return messageBuilder_.getMessageOrBuilder();
        } else {
          return message_ == null ?
              pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse.getDefaultInstance() : message_;
        }
      }
      /**
       * <code>.MessageResponse message = 2;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse, pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse.Builder, pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponseOrBuilder> 
          getMessageFieldBuilder() {
        if (messageBuilder_ == null) {
          messageBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse, pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponse.Builder, pers.enoch.im.common.generate.MessageResponseOuterClass.MessageResponseOrBuilder>(
                  getMessage(),
                  getParentForChildren(),
                  isClean());
          message_ = null;
        }
        return messageBuilder_;
      }

      private pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse user_;
      private com.google.protobuf.SingleFieldBuilderV3<
          pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse, pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse.Builder, pers.enoch.im.common.generate.UserResponseOuterClass.UserResponseOrBuilder> userBuilder_;
      /**
       * <code>.UserResponse user = 3;</code>
       * @return Whether the user field is set.
       */
      public boolean hasUser() {
        return userBuilder_ != null || user_ != null;
      }
      /**
       * <code>.UserResponse user = 3;</code>
       * @return The user.
       */
      public pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse getUser() {
        if (userBuilder_ == null) {
          return user_ == null ? pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse.getDefaultInstance() : user_;
        } else {
          return userBuilder_.getMessage();
        }
      }
      /**
       * <code>.UserResponse user = 3;</code>
       */
      public Builder setUser(pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse value) {
        if (userBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          user_ = value;
          onChanged();
        } else {
          userBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.UserResponse user = 3;</code>
       */
      public Builder setUser(
          pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse.Builder builderForValue) {
        if (userBuilder_ == null) {
          user_ = builderForValue.build();
          onChanged();
        } else {
          userBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.UserResponse user = 3;</code>
       */
      public Builder mergeUser(pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse value) {
        if (userBuilder_ == null) {
          if (user_ != null) {
            user_ =
              pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse.newBuilder(user_).mergeFrom(value).buildPartial();
          } else {
            user_ = value;
          }
          onChanged();
        } else {
          userBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.UserResponse user = 3;</code>
       */
      public Builder clearUser() {
        if (userBuilder_ == null) {
          user_ = null;
          onChanged();
        } else {
          user_ = null;
          userBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.UserResponse user = 3;</code>
       */
      public pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse.Builder getUserBuilder() {
        
        onChanged();
        return getUserFieldBuilder().getBuilder();
      }
      /**
       * <code>.UserResponse user = 3;</code>
       */
      public pers.enoch.im.common.generate.UserResponseOuterClass.UserResponseOrBuilder getUserOrBuilder() {
        if (userBuilder_ != null) {
          return userBuilder_.getMessageOrBuilder();
        } else {
          return user_ == null ?
              pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse.getDefaultInstance() : user_;
        }
      }
      /**
       * <code>.UserResponse user = 3;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse, pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse.Builder, pers.enoch.im.common.generate.UserResponseOuterClass.UserResponseOrBuilder> 
          getUserFieldBuilder() {
        if (userBuilder_ == null) {
          userBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse, pers.enoch.im.common.generate.UserResponseOuterClass.UserResponse.Builder, pers.enoch.im.common.generate.UserResponseOuterClass.UserResponseOrBuilder>(
                  getUser(),
                  getParentForChildren(),
                  isClean());
          user_ = null;
        }
        return userBuilder_;
      }

      private java.lang.Object createTime_ = "";
      /**
       * <code>string create_time = 4;</code>
       * @return The createTime.
       */
      public java.lang.String getCreateTime() {
        java.lang.Object ref = createTime_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          createTime_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string create_time = 4;</code>
       * @return The bytes for createTime.
       */
      public com.google.protobuf.ByteString
          getCreateTimeBytes() {
        java.lang.Object ref = createTime_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          createTime_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string create_time = 4;</code>
       * @param value The createTime to set.
       * @return This builder for chaining.
       */
      public Builder setCreateTime(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        createTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string create_time = 4;</code>
       * @return This builder for chaining.
       */
      public Builder clearCreateTime() {
        
        createTime_ = getDefaultInstance().getCreateTime();
        onChanged();
        return this;
      }
      /**
       * <code>string create_time = 4;</code>
       * @param value The bytes for createTime to set.
       * @return This builder for chaining.
       */
      public Builder setCreateTimeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        createTime_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:Response)
    }

    // @@protoc_insertion_point(class_scope:Response)
    private static final pers.enoch.im.common.generate.ResponseOuterClass.Response DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new pers.enoch.im.common.generate.ResponseOuterClass.Response();
    }

    public static pers.enoch.im.common.generate.ResponseOuterClass.Response getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Response>
        PARSER = new com.google.protobuf.AbstractParser<Response>() {
      @java.lang.Override
      public Response parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Response(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Response> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Response> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public pers.enoch.im.common.generate.ResponseOuterClass.Response getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Response_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016Response.proto\032\025MessageResponse.proto\032" +
      "\022UserResponse.proto\"m\n\010Response\022\014\n\004type\030" +
      "\001 \001(\005\022!\n\007message\030\002 \001(\0132\020.MessageResponse" +
      "\022\033\n\004user\030\003 \001(\0132\r.UserResponse\022\023\n\013create_" +
      "time\030\004 \001(\tB!\n\035pers.enoch.im.common.gener" +
      "ateH\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          pers.enoch.im.common.generate.MessageResponseOuterClass.getDescriptor(),
          pers.enoch.im.common.generate.UserResponseOuterClass.getDescriptor(),
        });
    internal_static_Response_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Response_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Response_descriptor,
        new java.lang.String[] { "Type", "Message", "User", "CreateTime", });
    pers.enoch.im.common.generate.MessageResponseOuterClass.getDescriptor();
    pers.enoch.im.common.generate.UserResponseOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
